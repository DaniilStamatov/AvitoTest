package daniilStamatov.AvitoTest.Controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "api")
@RequiredArgsConstructor
public class URLController {
    private final URLService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<Url> shortenUrl(@RequestBody String longUrl){
        Url url = urlService.saveUrl(longUrl);
        return ResponseEntity.status(HttpStatus.CREATED).body(url);
    }

    @GetMapping("{/shortUrl}")
    public ResponseEntity<Void> redirectToUrl(@PathVariable String shortUrl){
        String longUrl = urlService.getLongUrl(shortUrl);
        if(longUrl!=null){
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(longUrl));
            return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
