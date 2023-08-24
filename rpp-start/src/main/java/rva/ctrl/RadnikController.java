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
import rva.jpa.Radnik;
import rva.jpa.Sektor;
import rva.repository.ObrazovanjeRepository;
import rva.repository.RadnikRepository;
import rva.repository.SektorRepository;

@CrossOrigin
@RestController
@Api(tags = {"Radnik CRUD operations"})
public class RadnikController {
	
	@Autowired
	private RadnikRepository repositoryRadnik;
	
	@Autowired
	private SektorRepository repositorySektor;
	
	@Autowired
	private ObrazovanjeRepository repositoryObrazovanje;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	@ApiOperation(value = "Returns all rows from Radnik table in database")
	@GetMapping("/radnik")
	public Collection<Radnik> getAllRadnik(){
		return repositoryRadnik.findAll();
	}
	
	
	@ApiOperation(value = "Returns Radnik with specific id in database")
	@GetMapping("/radnik/{id}")
	public Radnik getRadnikById(@PathVariable int id) {
		return repositoryRadnik.getById(id);
	}
	
	
	@ApiOperation(value = "Returns Radnik by Sektor from Radnik table in database")
	@GetMapping("/radnik/bySektor/{sektor}")
	public Collection<Radnik> getRadnikBySektor(@PathVariable int sektor){
		Sektor tempSektor = repositorySektor.getById(sektor);
		return repositoryRadnik.findBySektor(tempSektor);
	}
	
	@ApiOperation(value = "Returns Radnik by Obrazovanje from Radnik table in database")
	@GetMapping("/radnik/byObrazovanje/{obrazovanje}")
	public Collection<Radnik> getRadnikByObrazovanje(@PathVariable int obrazovanje){
		Obrazovanje tempObrazovanje = repositoryObrazovanje.getById(obrazovanje);
		return repositoryRadnik.findByObrazovanje(tempObrazovanje);
	}
	
	@ApiOperation(value = "Creates new Radnik in Radnik table in database")
	@PostMapping("/radnik")
	public ResponseEntity<Radnik> createRadnik(@RequestBody Radnik radnik){
		if(repositoryRadnik.existsById(radnik.getIdRadnik())) {
			return new ResponseEntity<Radnik>(HttpStatus.CONFLICT);
		}else {
			Radnik temprRadnik = repositoryRadnik.save(radnik);
			return new ResponseEntity<Radnik>(temprRadnik, HttpStatus.CREATED);
		}
	}
	
	
	@ApiOperation(value = "A method that updates rows in Radnik table in database")
	@PutMapping("/radnik")
	public ResponseEntity<Radnik> updateRadnik(@RequestBody Radnik radnik){
		if(!repositoryRadnik.existsById(radnik.getIdRadnik())) {
			return new ResponseEntity<Radnik>(HttpStatus.NOT_FOUND);
		}else {
			repositoryRadnik.save(radnik);
			return new ResponseEntity<Radnik>(HttpStatus.OK);
		}
	}
	
	
	@ApiOperation(value = "A method that deletes rows from Radnik table in database")
	@DeleteMapping("/radnik/{id}")
	public ResponseEntity<Radnik> deleteRadnik(@PathVariable int id){
		if(repositoryRadnik.existsById(id)) {
			if(id==-100) {
				repositoryRadnik.deleteById(id);
				jdbcTemplate.execute("INSERT INTO radnik(\"id_radnik\", \"ime\", \"prezime\", \"broj_lk\", \"obrazovanje\", \"sektor\")" + "VALUES(-100, 'Ime', 'prezime', 123456, 2, 2)");
				return new ResponseEntity<Radnik>(HttpStatus.OK);
			}else {
				repositoryRadnik.deleteById(id);
				return new ResponseEntity<Radnik>(HttpStatus.OK);
			}
		}else {
			return new ResponseEntity<Radnik>(HttpStatus.NOT_FOUND);
		}
	}

}
