package com.CK.manager;

import com.CK.dto.request.UserSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.CK.constants.RestApiUrls.ADD;

/*
Microservisler arasında iletişimi RespAPI üzerinden sağlamak için kullanılır
Iletişim kurulacak servisin conntroller atamasına istek atar
İki adet parametreşini özellikle kullanmalıyız
1- url: istek atılacak controller sınıfına erişim adresini yazıyoruz.
2- name(optional): yazılan her bir manager için bir isim veriyoruz. zorunlu değilidir ancak
aynı ismi taşıyan birden fazla manager olursa sistem hata verir. Sorun anlamanız mümkün olmaya bilir.
Kullanırken dikkatli olun.
 */
@FeignClient(url = "http://localhost:9094/dev/v1/user", name = "userProfileManager")
public interface UserProfileManager {
    @PostMapping(ADD)
    ResponseEntity<Void> save(@RequestBody UserSaveRequestDto dto);
}
