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
import rva.jpa.Obrazovanje;
import rva.repository.ObrazovanjeRepository;

@CrossOrigin
@RestController
@Api(tags = {"Obrazovanje CRUD operations"})
public class ObrazovanjeController {
	
	@Autowired 
	private ObrazovanjeRepository repositoryObrazovanje;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	@ApiOperation(value = "Returns all rows from Obrazovanje table in database")
	@GetMapping("/obrazovanje")
	public Collection<Obrazovanje> getAllObrazovanje (){
		
		return repositoryObrazovanje.findAll();
	}
	
	
	@ApiOperation(value = "Returns Obrazovanje with specific id in database")
	@GetMapping("/obrazovanje/{id}") //Path varijabla - id - URL resurs
	public Obrazovanje getObrazovanjeById(@PathVariable int id) {
		
		return repositoryObrazovanje.getById(id);
	}
	
	
	@ApiOperation(value = "Returns Obrazovanje by name from Obrazovanje table in database")
	@GetMapping("/obrazovanje/naziv/{naziv}") //Path varijabla - naziv
	public Collection<Obrazovanje> getObrazovanjeByNaziv(@PathVariable String naziv){
		
		return repositoryObrazovanje.findByNazivContainingIgnoreCase(naziv);
	}
	
	
	@ApiOperation(value = "Creates new Obrazovanje in Obrazovanje table in database")
	@PostMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> createObrazovanje(@RequestBody Obrazovanje obrazovanje){
		if(!repositoryObrazovanje.existsById(obrazovanje.getIdObrazovanje())) {
			Obrazovanje temp = repositoryObrazovanje.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(temp, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.CONFLICT);
		}
	}
	
	
	@ApiOperation(value = "A method that updates rows in Obrazovanje table in database")
	@PutMapping("/obrazovanje")
	public ResponseEntity<Obrazovanje> updateObrazovanjEntity (@RequestBody Obrazovanje obrazovanje){
		if(repositoryObrazovanje.existsById(obrazovanje.getIdObrazovanje())) {
			repositoryObrazovanje.save(obrazovanje);
			return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@ApiOperation(value = "A method that deletes rows from Obrazovanje table in database")
	@DeleteMapping("/obrazovanje/{id}")
	public ResponseEntity<Obrazovanje> deleteObrazovanje(@PathVariable Integer id){
		if(repositoryObrazovanje.existsById(id)) {
			if(id==100) {
				repositoryObrazovanje.deleteById(id);
				jdbcTemplate.execute("insert into obrazovanje(\"id_obrazovanje\", \"naziv\", \"stepen_strucne_spreme\", \"opis\")" + "values(-100, 'Test naziv', 'Test sp', 'Test opis')");
				return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
			}else {
				repositoryObrazovanje.deleteById(id);
				return new ResponseEntity<Obrazovanje>(HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<Obrazovanje>(HttpStatus.NOT_FOUND);
		}
	}
	

}
