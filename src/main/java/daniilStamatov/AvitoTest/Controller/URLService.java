package daniilStamatov.AvitoTest.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class URLService {
    private URLRepository urlRepository;

    @Transactional
    public Url saveUrl(String longUrl){
        Url url = new Url();
        String shortUrl = generateShortUrl();
        url.setShortUrl(shortUrl);
        url.setLongUrl(longUrl);
        return urlRepository.save(url);
    }

    @Transactional(readOnly = true)
    public String getLongUrl(String shortUrl){
        Url url = urlRepository.findByShortURL(shortUrl).orElseThrow(()-> new EntityDoesNotExistException("Такого предмета не существует"));

        return url.getLongUrl();
    }

    private String generateShortUrl() {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i<6; i++){
            stringBuilder.append(chars.charAt(random.nextInt(chars.length())));
        }
        return stringBuilder.toString();
    }
}
