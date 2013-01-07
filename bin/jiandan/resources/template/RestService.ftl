package ${pkg}.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ${longName};
import ${pkg}.dao.${shortName}Dao;

@Service
@RequestMapping("/${shortName}")
public class ${shortName}Service {

	@Autowired
	${shortName}Dao dao;

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	String create(@RequestBody ${shortName} e) {
		try {
			dao.save(e);
		} catch (Exception ex) {
			return "Failed: " + ex.getMessage();
		}
		return "200 OK";
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody
	${shortName} read(@PathVariable("id") Long id) {
		return dao.findOne(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	String update(@PathVariable("id") Long id, @RequestBody ${shortName} e) {
		if (dao.exists(id)) {
			dao.save(e);
			return "200 OK";
		} else {
			return create(e);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	String delete(@PathVariable("id") Long id) {
		if (dao.exists(id)) {
			dao.delete(id);
			return "200 OK";
		} else {
			return "ID NOT FOUND : " + id;
		}
	}
}
