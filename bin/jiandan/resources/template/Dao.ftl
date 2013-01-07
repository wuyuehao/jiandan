package ${pkg}.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ${longName};

@Repository
public interface ${shortName}Dao extends JpaRepository<${shortName}, Long> {

	public List<${shortName}> findAll();

}