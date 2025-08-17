package org.rahmasir.fmuserservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.rahmasir.fmuserservice.dto.SkillDto;
import org.rahmasir.fmuserservice.entity.Skill;
import org.rahmasir.fmuserservice.mapper.UserMapper;
import org.rahmasir.fmuserservice.repository.SkillRepository;
import org.rahmasir.fmuserservice.service.SkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of the SkillService interface.
 */
@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<SkillDto> getAllSkills() {
        return skillRepository.findAll().stream()
                .map(userMapper::toSkillDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Set<Skill> findOrCreateSkills(Set<String> skillNames) {
        return skillNames.stream()
                .map(name -> skillRepository.findByNameIgnoreCase(name)
                        .orElseGet(() -> skillRepository.save(new Skill(name))))
                .collect(Collectors.toSet());
    }
}
