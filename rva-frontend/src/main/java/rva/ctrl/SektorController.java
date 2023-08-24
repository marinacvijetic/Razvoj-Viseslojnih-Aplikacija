package rva.ctrl;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import rva.jpa.Sektor;
import rva.repository.SektorRepository;

@CrossOrigin
@RestController
@Api(tags = {"Sektor CRUD operations"})
public class SektorController {

	@Autowired
	private SektorRepository repositorySektor;
	
//	@Autowired
//	private PreduzeceRepository repositoryPreduzece;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all rows from Sektor table in database")
	@GetMapping("/sektor")
	Collection<Sektor> getAllSektor(){
		return repositorySektor.findAll();
	}
	
	@ApiOperation(value = "Returns Sektor with specific id in database")
	@GetMapping("/sektor/{id}")
	public Sektor getSektorById(@PathVariable int id) {
		return repositorySektor.getById(id);
	}
	
	@ApiOperation(value = "Returns Sektor by name from Sektor table in database")
	@GetMapping("/sektor/naziv/{naziv}")
	public Collection<Sektor> getSektorByName(@PathVariable String naziv){
		return repositorySektor.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Creates new Sektor in Sektor table in database")
	@PostMapping("/sektor")
	public ResponseEntity<Sektor> createSektor(@RequestBody Sektor sektor){
		if(!repositorySektor.existsById(sektor.getId())) {
			Sektor tempSektor = repositorySektor.save(sektor);
			return new ResponseEntity<Sektor>(tempSektor, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Sektor>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "A method that updates rows in Sektor table in database")
	@PutMapping("/sektor")
	public ResponseEntity<Sektor> updateSektor(@RequestBody Sektor sektor){
		if(repositorySektor.existsById(sektor.getId())) {
			repositorySektor.save(sektor);
			return new ResponseEntity<Sektor>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Sektor>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "A method that deletes rows from Sektor table in database")
	@DeleteMapping("/sektor/{id}")
	public ResponseEntity<Sektor> deleteSektor(@PathVariable int id){
		if(repositorySektor.existsById(id)){
			if(id == -100) {
				repositorySektor.deleteById(id);
				jdbcTemplate.execute("insert into sektor(\"id\", \"naziv\", \"oznaka\", \"preduzece\")" + "values(-100, 'Test naziv', 'Test ozn', 3)");
				return new ResponseEntity<Sektor>(HttpStatus.OK);
			}else {
				repositorySektor.deleteById(id);
				return new ResponseEntity<Sektor>(HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<Sektor>(HttpStatus.NOT_FOUND);
		}
	}
}
