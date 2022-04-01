package com.example.restaurants.naver;

import com.example.restaurants.naver.dto.SearchImageReq;
import com.example.restaurants.naver.dto.SearchLocalReq;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class NaverClientTest {

    @Autowired
    private NaverClient naverClient;

    @Test
    public void searchLocalTest() {

        var search = new SearchLocalReq();
        search.setQuery("토스");

        var result = naverClient.searchLocal(search);
        System.out.println(result);
    }

    @Test
    public void searchImageTest(){

        var search = new SearchImageReq();
        search.setQuery("깃허브");

        var result = naverClient.searchImage(search);
        System.out.println(result);
    }

    @Test
    public void allTest(){

        var search1 = new SearchLocalReq();
        search1.setQuery("롯데월드");
        var search2 = new SearchImageReq();
        search2.setQuery("롯데월드");

        var result = naverClient.searchLocal(search1);
        var result2 = naverClient.searchImage(search2);

        System.out.println(result + " " + result2);

    }
}
