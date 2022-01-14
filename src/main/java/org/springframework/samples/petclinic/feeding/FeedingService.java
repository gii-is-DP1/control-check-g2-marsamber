package org.springframework.samples.petclinic.feeding;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FeedingService {

    private FeedingRepository feedingRepository;
	

	@Autowired
	public FeedingService(FeedingRepository feedingRepository) {
		this.feedingRepository = feedingRepository;
	}

    @Transactional
    public List<Feeding> getAll(){
        return this.feedingRepository.findAll();
    }
    @Transactional
    public List<FeedingType> getAllFeedingTypes(){
        return this.feedingRepository.findAllFeedingTypes();
    }

    @Transactional
    public FeedingType getFeedingType(String typeName) {
        return this.feedingRepository.findFeedingTypesByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleFeedingException.class)
    public Feeding save(Feeding p) throws UnfeasibleFeedingException {
        FeedingType feedingType = p.getFeedingType();
        if(p.getPet().getType().getId() != feedingType.getPetType().getId()){
            throw new UnfeasibleFeedingException();
        }else{
            return this.feedingRepository.save(p);
        }     
    }

    
}
