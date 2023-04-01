package com.jspapps.droneapp.domain.usecase;

import com.jspapps.droneapp.application.util.constant.DroneState;
import com.jspapps.droneapp.domain.event.*;
import com.jspapps.droneapp.domain.port.DeletePayloadDronePort;
import com.jspapps.droneapp.domain.port.ListMedicationLoadPort;
import com.jspapps.droneapp.domain.port.UpdateDronePort;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@Component
public class DroneLoadEventHandler {

    private final Logger logger = Logger.getLogger(DroneLoadEventHandler.class.getName());
    private final ApplicationEventPublisher eventPublisher;

    private final ListMedicationLoadPort listMedicationLoadPort;
    private final UpdateDronePort updateDronePort;
    private final DeletePayloadDronePort deletePayloadDronePort;

    @EventListener
    public void listenerReviewDroneLoadEvent(ReviewDroneLoadEvent reviewDroneLoadEvent) {
        try {
            logger.log(Level.INFO, "---> Checking drone load");

            var loadDroneWeight = listMedicationLoadPort.currentMedicationLoadByDrone(reviewDroneLoadEvent.getDroneId());
            if (loadDroneWeight < 500L) {
                logger.log(Level.INFO, MessageFormat.format("---> Drone {0} still is enable to load.", reviewDroneLoadEvent.getDroneId()));
                updateDronePort.updateDroneState(reviewDroneLoadEvent.getDroneId(), DroneState.LOADING);
            } else {
                logger.log(Level.INFO, MessageFormat.format("---> Drone {0} is full, changing state to loaded ...", reviewDroneLoadEvent.getDroneId()));
                updateDronePort.updateDroneState(reviewDroneLoadEvent.getDroneId(), DroneState.LOADED);
                eventPublisher.publishEvent(new LoadedDroneEvent(reviewDroneLoadEvent.getDroneId()));
            }

        } catch (DataAccessException exception) {
            logger.log(Level.SEVERE, MessageFormat.format("An error ocurred on review drone load event. {0}", exception.getMessage()));
        }
    }

    @EventListener
    public void listenerLoadedDroneEvent(LoadedDroneEvent loadedDroneEvent) {
        try {
            logger.log(Level.INFO, MessageFormat.format("---> Preparing drone {0} to delivering.", loadedDroneEvent.getDroneId()));
            Thread.sleep(10000);

            logger.log(Level.INFO, MessageFormat.format("---> Drone {0} is already, changing state to delivering ...", loadedDroneEvent.getDroneId()));
            updateDronePort.updateDroneState(loadedDroneEvent.getDroneId(), DroneState.DELIVERING);
            eventPublisher.publishEvent(new DeliveringDroneEvent(loadedDroneEvent.getDroneId()));

        } catch (InterruptedException | DataAccessException exception ) {
            logger.log(Level.SEVERE, MessageFormat.format("An error ocurred on loaded drone event. {0}", exception.getMessage()));
        }
    }

    @EventListener
    public void listenerDeliveringDroneEvent(DeliveringDroneEvent deliveringDroneEvent) {
        try {
            logger.log(Level.INFO, MessageFormat.format("---> Preparing drone {0} to delivered.", deliveringDroneEvent.getDroneId()));
            Thread.sleep(10000);

            logger.log(Level.INFO, MessageFormat.format("---> Drone {0} is already, changing state to delivered ...", deliveringDroneEvent.getDroneId()));
            updateDronePort.updateDroneState(deliveringDroneEvent.getDroneId(), DroneState.DELIVERED);
            eventPublisher.publishEvent(new DeliveredDroneEvent(deliveringDroneEvent.getDroneId()));

        } catch (InterruptedException | DataAccessException exception ) {
            logger.log(Level.SEVERE, MessageFormat.format("An error ocurred on delivering drone event. {0}", exception.getMessage()));
        }
    }

    @EventListener
    public void listenerDeliveredDroneEvent(DeliveredDroneEvent deliveredDroneEvent) {
        try {
            logger.log(Level.INFO, MessageFormat.format("---> Preparing drone {0} to returning.", deliveredDroneEvent.getDroneId()));
            Thread.sleep(10000);

            logger.log(Level.INFO, MessageFormat.format("---> Drone {0} is already, changing state to returning ...", deliveredDroneEvent.getDroneId()));
            updateDronePort.updateDroneState(deliveredDroneEvent.getDroneId(), DroneState.RETURNING);
            eventPublisher.publishEvent(new ReturningDroneEvent(deliveredDroneEvent.getDroneId()));

        } catch (InterruptedException | DataAccessException exception ) {
            logger.log(Level.SEVERE, MessageFormat.format("An error ocurred on delivered drone event. {0}", exception.getMessage()));
        }
    }

    @EventListener
    public void listenerReturningDroneEvent(ReturningDroneEvent returningDroneEvent) {
        try {
            logger.log(Level.INFO, MessageFormat.format("---> Preparing drone {0} to idle.", returningDroneEvent.getDroneId()));
            Thread.sleep(10000);

            logger.log(Level.INFO, MessageFormat.format("---> Drone {0} is already, changing state to idle ...", returningDroneEvent.getDroneId()));
            updateDronePort.updateDroneState(returningDroneEvent.getDroneId(), DroneState.IDLE);
            eventPublisher.publishEvent(new IdleDroneEvent(returningDroneEvent.getDroneId()));

        } catch (InterruptedException | DataAccessException exception ) {
            logger.log(Level.SEVERE, MessageFormat.format("An error ocurred on delivered drone event. {0}", exception.getMessage()));
        }
    }

    @EventListener
    public void listenerIdleDroneEvent(IdleDroneEvent idleDroneEvent) {
        deletePayloadDronePort.cleanLoadDeliveredByDrone(idleDroneEvent.getDroneId());
    }
}
