package codes.mydna.services.impl;

import codes.mydna.entities.AnalysisRequestEntity;
import codes.mydna.exceptions.NotFoundException;
import codes.mydna.lib.AnalysisRequest;
import codes.mydna.lib.Dna;
import codes.mydna.mappers.AnalysisRequestMapper;
import codes.mydna.services.AnalysisRequestService;
import codes.mydna.utils.EntityList;
import codes.mydna.validation.Assert;
import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnalysisRequestServiceImpl implements AnalysisRequestService {

    public static final Logger LOG = Logger.getLogger(AnalysisRequestService.class.getName());

    @Inject
    private EntityManager em;

    @Override
    public EntityList<AnalysisRequest> getAnalysisRequests(QueryParameters qp) {
        List<AnalysisRequest> requests = JPAUtils.queryEntities(em, AnalysisRequestEntity.class, qp)
                .stream()
                .map(AnalysisRequestMapper::fromEntity)
                .collect(Collectors.toList());
        Long count = JPAUtils.queryEntitiesCount(em, AnalysisRequestEntity.class);
        return new EntityList<>(requests, count);
    }

    @Override
    public AnalysisRequest getAnalysisRequest(String id) {
        Assert.fieldNotNull(id, "id");

        AnalysisRequestEntity entity = getAnalysisRequestEntity(id);
        AnalysisRequest request = AnalysisRequestMapper.fromEntity(entity);
        if (request == null)
            throw new NotFoundException(AnalysisRequest.class, id);

        return request;
    }

    @Override
    public AnalysisRequest insertAnalysisRequest(AnalysisRequest request) {


        Assert.objectNotNull(request, Dna.class);
        Assert.fieldNotEmpty(request.getDnaId(), "dnaId", AnalysisRequest.class);

        AnalysisRequestEntity entity = AnalysisRequestMapper.toEntity(request);

        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();

        return AnalysisRequestMapper.fromEntity(entity);
    }

    @Override
    public boolean removeAnalysisRequest(String id) {

        AnalysisRequestEntity entity = getAnalysisRequestEntity(id);
        if (entity == null)
            return false;

        em.getTransaction().begin();
        em.remove(entity);
        em.getTransaction().commit();

        return true;
    }

    private AnalysisRequestEntity getAnalysisRequestEntity(String id){
        if (id == null)
            return null;
        return em.find(AnalysisRequestEntity.class, id);
    }

}
