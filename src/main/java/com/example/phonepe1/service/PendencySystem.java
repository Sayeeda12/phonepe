package com.example.phonepe1.service;

import com.example.phonepe1.model.Entity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PendencySystem implements TrackingService {

    Map<Integer, List<String>> entityList = new HashMap<>();
    List<List<String>> tagsList = new ArrayList<>(); //Maintain a separate obj with just tags for similar tags check

    /*
    * For an entity to be tracked, it should follow all criteria -
    *   1. It should have unique ID
    *   2. It should have at least one tag
    *   3. It should have unique combination of tags (Limitation - same tags hierarchy with new ID won't be tracked)
    * * */
    @Override
    public void startTracking(Entity entity) {
        //If an entity is already being tracked, don't track again
        if(entityList.get(entity.getId()) == null && !tagsList.contains(entity.getTags()) && !entity.getTags().isEmpty()) {
            entityList.put(entity.getId(), entity.getTags());
            tagsList.add(entity.getTags());
        }
        else {
            log.error("Either the tags are empty or an entity with the same ID {} or the same pair of tags {} is already being tracked", entity.getId(), entity.getTags());
        }
    }

    @Override
    public void stopTracking(Integer id) {
        //Stop tracking if the ID is being tracked, remove from both objects
        if(entityList.get(id) != null) {
            tagsList.remove(entityList.get(id));
            entityList.remove(id);
        } else {
            log.error("No entity with ID {} is being tracked", id);
        }
    }

    /**
     * * THIS METHOD CAN BE IMPROVED BY USING A DOUBLE ENDED LINKED LIST MAINTAINING TAGS AND IT'S LINKS
     * THAT CAN HELP FIND COUNT FASTER, DUE TO TIME CONSTRAINTS, COULDN'T DO IT
     * * *
     */
    @Override
    public int getCounts(List<String> tags) {
        //If tags is empty, return 0;
        if(tags.isEmpty())
            return 0;

        int count = 0;

        //Iterate through all lists to check if such a tag combination is being tracked
        for(int i=0; i < tagsList.size(); i++) {
            int j=0;
            //If tags size is bigger than the current tagsList to be compared, skip
            if(tagsList.get(i).size() < tags.size()) {
                continue;
            } else {
                for (j = 0; j < tags.size(); j++) {
                    if (!tagsList.get(i).get(j).equals(tags.get(j))) {
                        break;
                    }
                }
                //If all elements are same, increment counter
                if(j == tags.size())
                    count++;
            }
        }
        return count;
    }
}
