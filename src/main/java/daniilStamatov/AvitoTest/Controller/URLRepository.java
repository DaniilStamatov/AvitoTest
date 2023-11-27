package daniilStamatov.AvitoTest.Controller;

import org.springframework.data.jpa.repository.JpaRepository;

import java.net.URL;
import java.util.Optional;

public interface URLRepository extends JpaRepository<Url, Long> {

    Optional<Url> findByShortURL(String shortUrl);
}
