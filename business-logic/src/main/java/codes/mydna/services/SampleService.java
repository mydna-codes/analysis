package codes.mydna.services;

import codes.mydna.entities.SampleEntity;

import java.util.List;

/**
 * @see codes.mydna.services.impl.SampleServiceImpl
 */
public interface SampleService {

    List<SampleEntity> getSamples();
    SampleEntity addSampleEntity(SampleEntity entity);

}