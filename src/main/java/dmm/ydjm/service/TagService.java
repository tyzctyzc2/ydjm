package dmm.ydjm.service;

import dmm.ydjm.model.TagRequest;
import dmm.ydjm.repository.PostTagRepository;
import dmm.ydjm.repository.TagDefinition;
import dmm.ydjm.repository.TagDefinitionRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagService {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TagService.class);

    @Autowired
    private TagDefinitionRepository tagDefinitionRepository;

    @Autowired
    private PostTagRepository postTagRepository;

    public List<TagRequest> getAllTags() {
        List<TagDefinition> allTagFromDB = tagDefinitionRepository.findAll();
        List<TagRequest> tagJSON = new ArrayList<>();
        for (TagDefinition tagDefinition: allTagFromDB) {
            TagRequest tagRequest = new TagRequest();
            tagRequest.setTagId(tagDefinition.getTagId());
            tagRequest.setTagName(tagDefinition.getTagName());
            tagJSON.add(tagRequest);
        }
        return tagJSON;
    }
}
