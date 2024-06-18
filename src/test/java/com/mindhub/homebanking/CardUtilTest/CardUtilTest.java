package com.mindhub.homebanking.CardUtilTest;
import com.mindhub.homebanking.utils.CardUtils;
import com.mindhub.homebanking.utils.RandomNumbers;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
public class CardUtilTest {

//    @Test
//    public void cardNumberIsCreated() {
//        String cardNumber = CardUtils.generateCardNumber();
//        assertThat(cardNumber, is(not(emptyOrNullString())));
//    }
//
//    @Test
//    public void cvvIsCreated() {
//        int cvv = CardUtils.getCVV();
//        assertThat(cvv, is(greaterThanOrEqualTo(100)));
//        assertThat(cvv, is(lessThanOrEqualTo(999)));
//    }
//    @Test
//    public void getCVVGeneratesRandomCVV() {
//        int cvv1 = CardUtils.getCVV();
//        int cvv2 = CardUtils.getCVV();
//        assertThat(cvv1, is(not(equalTo(cvv2))));
//    }
//
//    @Test
//    public void getCardNumberReturns16DigitNumber() {
//        String cardNumber = CardUtils.getCardNumber();
//        assertThat(cardNumber.length(), is(16));
//    }
//
//    @Test
//    public void getCardNumberReturnsDifferentNumbers() {
//        String cardNumber1 = CardUtils.getCardNumber();
//        String cardNumber2 = CardUtils.getCardNumber();
//        assertThat(cardNumber1, is(not(equalTo(cardNumber2))));
//    }

}
