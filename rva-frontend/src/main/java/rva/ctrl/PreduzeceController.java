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
import rva.jpa.Preduzece;
import rva.repository.PreduzeceRepository;

@CrossOrigin
@RestController
@Api(tags = {"Preduzece CRUD operations"})
public class PreduzeceController {
	
	@Autowired
	private PreduzeceRepository repositoryPreduzece;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@ApiOperation(value = "Returns all rows from Preduzece table in database")
	@GetMapping("/preduzece")
	Collection<Preduzece> getAllPreduzece(){
		return repositoryPreduzece.findAll();
	}
	
	@ApiOperation(value = "Returns Preduzece with specific id in database")
	@GetMapping("/preduzece/{id}")
	public Preduzece getPreduzeceById(@PathVariable int id) {
		return repositoryPreduzece.getById(id);
	}
	
	@ApiOperation(value = "Returns Preduzece by name from Preduzece table in database")
	@GetMapping("/preduzece/naziv/{naziv}")
	public Collection<Preduzece> getPreduzeceByName(@PathVariable String naziv){
		return repositoryPreduzece.findByNazivContainingIgnoreCase(naziv);
	}
	
	@ApiOperation(value = "Creates new Preduzece in Preduzece table in database")
	@PostMapping("/preduzece")
	public ResponseEntity<Preduzece> createPreduzece(@RequestBody Preduzece preduzece){
		if(!repositoryPreduzece.existsById(preduzece.getId())) {
			Preduzece tempPreduzece = repositoryPreduzece.save(preduzece);
			return new ResponseEntity<Preduzece>(tempPreduzece, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Preduzece>(HttpStatus.CONFLICT);
		}
	}
	
	@ApiOperation(value = "A method that updates rows in Preduzece table in database")
	@PutMapping("/preduzece")
	public ResponseEntity<Preduzece> updatePreduzece(@RequestBody Preduzece preduzece){
		if(repositoryPreduzece.existsById(preduzece.getId())){
			repositoryPreduzece.save(preduzece);
			return new ResponseEntity<Preduzece>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Preduzece>(HttpStatus.NOT_FOUND);
		}
	}
	
	@ApiOperation(value = "A method that deletes rows from Preduzece table in database")
	@DeleteMapping("/preduzece/{id}")
	public ResponseEntity<Preduzece> deletePreduzece(@PathVariable int id){
		if(repositoryPreduzece.existsById(id)) {
			if(id == -100) {
				repositoryPreduzece.deleteById(id);
				jdbcTemplate.execute("insert into preduzece(\"id\", \"naziv\", \"pib\", \"sediste\", \"opis\")" + "values(-100, 'Test naziv', 123456, 'Test sediste', 'Test opis')");
				return new ResponseEntity<Preduzece>(HttpStatus.OK);
			}else {
				repositoryPreduzece.deleteById(id);
				return new ResponseEntity<Preduzece>(HttpStatus.OK);
				
			}
			
			
		}else {
			return new ResponseEntity<Preduzece>(HttpStatus.NOT_FOUND);
		}
	}
	

}
