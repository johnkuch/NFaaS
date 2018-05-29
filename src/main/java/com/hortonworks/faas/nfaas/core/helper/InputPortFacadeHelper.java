package com.hortonworks.faas.nfaas.core.helper;

import org.apache.nifi.web.api.dto.PortDTO;
import org.apache.nifi.web.api.entity.PortEntity;
import org.apache.nifi.web.api.entity.ProcessGroupFlowEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashSet;
import java.util.Set;

public class InputPortFacadeHelper extends BaseFacadeHelper {

    private static final Logger logger = LoggerFactory.getLogger(InputPortFacadeHelper.class);

    /**
     * This is the method to get all the input port entity from the pgfe
     *
     * @param pgfe
     * @param inputPortFromTemplate
     * @return
     */
    public Set<PortEntity> getInputPortsEntityForUndeploy(ProcessGroupFlowEntity pgfe,
                                                          Set<PortDTO> inputPortFromTemplate) {

        Set<PortEntity> resultInputPorts = new LinkedHashSet<>();
        Set<PortEntity> allInputPorts = pgfe.getProcessGroupFlow().getFlow().getInputPorts();

        Set<String> inputPortsNameFromTemplate = templateFacadeHelper.getAllInputPortNameFromTemplate(inputPortFromTemplate);

        for (PortEntity pe : allInputPorts) {
            if (inputPortsNameFromTemplate.contains(pe.getComponent().getName())) {
                resultInputPorts.add(pe);
            }

        }
        return resultInputPorts;
    }
}
