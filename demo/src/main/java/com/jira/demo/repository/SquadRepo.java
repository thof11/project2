package com.jira.demo.repository;

import com.jira.demo.model.Squad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SquadRepo extends JpaRepository<Squad, Long> {
}
