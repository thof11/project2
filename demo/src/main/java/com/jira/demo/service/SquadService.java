package com.jira.demo.service;

import com.jira.demo.exception.NotFoundException;
import com.jira.demo.model.Employee;
import com.jira.demo.model.Squad;
import com.jira.demo.repository.EmployeeRepo;
import com.jira.demo.repository.SquadRepo;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SquadService {

    private final SquadRepo squadRepo;
    private final EmployeeRepo employeeRepo;

    public List<Squad> getAllSquads() {
        return squadRepo.findAll();
    }

    public Squad getSquadById(long id) {
        return squadRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found with id: " + id));
    }

    public Squad createSquad(Squad squad) {
        return squadRepo.save(squad);
    }

    public Squad updateSquad(long id, Squad squad) {
        Squad existingSquad = getSquadById(id);
        existingSquad.setSquadName(squad.getSquadName());
        existingSquad.setDescription(squad.getDescription());
        return squadRepo.save(existingSquad);
    }

    public void deleteSquad(long id) {
        squadRepo.deleteById(id);
    }

    public void deleteAllSquads() {
        squadRepo.deleteAll();
    }

    @Transactional
    public Squad addEmployeeToSquad(Long squadId, Long employeeId) {
        Squad squad = squadRepo.findById(squadId)
                .orElseThrow(() -> new NotFoundException("Squad not found with id: " + squadId));

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id" + employeeId));

        squad.addEmployee(employee);
        return squadRepo.save(squad);
    }

    @Transactional
    public Squad removeEmployeeFromSquad(Long squadId, Long employeeId) {
        Squad squad = squadRepo.findById(squadId)
                .orElseThrow(() -> new NotFoundException("Squad not found with id:" + squadId));

        Employee employee = employeeRepo.findById(employeeId)
                .orElseThrow(() -> new NotFoundException("Employee not found with id" + employeeId));

        squad.removeEmployee(employee);
        return squadRepo.save(squad);
    }
}
