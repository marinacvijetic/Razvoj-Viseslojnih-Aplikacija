package rva.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.jpa.Obrazovanje;
import rva.jpa.Radnik;
import rva.jpa.Sektor;

public interface RadnikRepository extends JpaRepository<Radnik, Integer> {
	
	Collection<Radnik> findBySektor(Sektor sektor);
	Collection<Radnik> findByObrazovanje(Obrazovanje obrazovanje);

}
