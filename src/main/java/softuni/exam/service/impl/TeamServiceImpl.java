package softuni.exam.service.impl;


import org.springframework.stereotype.Service;
import softuni.exam.service.TeamService;

import javax.transaction.Transactional;

@Service
@Transactional
public class TeamServiceImpl implements TeamService {

    @Override
    public String importTeams() {
       return "";
    }

    @Override
    public boolean areImported() {
        return false;
    }

    @Override
    public String readTeamsXmlFile() {
        return "";
    }

}
