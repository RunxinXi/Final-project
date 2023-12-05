package mie.ether_example;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;

import edu.toronto.dbservice.types.ClientRequest;

public class CompareRequests implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        // Assuming currentRequest is a map with keys 'itemType' and 'quantity'
    	ClientRequest requestId = (ClientRequest) execution.getVariable("currentClientRequest");

        // Retrieve the item type and requested quantity from the current request
        String itemType = (String) requestId.getItem();
        int requestedQuantity = (Integer) requestId.getQuantity();

        // Retrieve inventory quantities
        int quantityA = (Integer) execution.getVariable("QuantityA");
        int quantityB = (Integer) execution.getVariable("QuantityB");
        int quantityC = (Integer) execution.getVariable("QuantityC");

        // Check if the requested quantity is smaller than the inventory
        boolean canFulfill = false;
        switch (itemType) {
            case "A":
                canFulfill = requestedQuantity <= quantityA;
                break;
            case "B":
                canFulfill = requestedQuantity <= quantityB;
                break;
            case "C":
                canFulfill = requestedQuantity <= quantityC;
                break;
        }

        // Set a process variable indicating whether the request can be fulfilled
        execution.setVariable("canFulfillRequest", canFulfill);
        execution.setVariable("itemType", itemType);
    }
}
