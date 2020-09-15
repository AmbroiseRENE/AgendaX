package com.fr.Agenda.AgendaX.repository;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.stereotype.Repository;

import com.fr.Agenda.AgendaX.entity.User;

@Repository
public interface IUserRepository extends JpaRepositoryImplementation<User, Long>, IAbstractRepository<User, Long> {

	public List<User> findByAdress(String adress);

	public List<User> findByLastName(String lastName);

	public List<User> findByLaboratory(String laboratory);

	public List<User> findByCity(String city);

	public User findByEmailAndPwd(String email, String pwd);

	public boolean existsByEmail(String email);

	public User findByEmail(String email);
}
