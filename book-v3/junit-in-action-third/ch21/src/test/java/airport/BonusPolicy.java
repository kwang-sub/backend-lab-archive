package airport;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import mileage.Mileage;
import org.assertj.core.api.Assertions;

import static org.assertj.core.api.Assertions.*;

public class BonusPolicy {
    private Passenger mike;
    private Mileage mileage;

    @Given("^마일리지와 일반 승객이 있는 상황에서$")
    public void 마일리지와_일반_승객이_있는_상황에서() throws Throwable {
        mike = new Passenger("Mike", false);
        mileage = new Mileage();
    }

    @When("^일반 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
    public void 일반_승객이_가지고_있는_마일리지가_과_와_일_때(int arg1, int arg2, int arg3) throws Throwable {
        mileage.addMileage(mike, arg1);
        mileage.addMileage(mike, arg2);
        mileage.addMileage(mike, arg3);
    }

    @Then("^일반 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void 일반_승객의_보너스_포인트는_가_된다(int arg1) throws Throwable {
        mileage.calculateGivenPoints();
        assertThat(arg1).isEqualTo(mileage.getPassengersPointsMap().get(mike).intValue());
    }

    @Given("^마일리지와 VIP 승객이 있는 상황에서$")
    public void 마일리지와_VIP_승객이_있는_상황에서() throws Throwable {
        mike = new Passenger("Mike", true);
        mileage = new Mileage();
    }

    @When("^VIP 승객이 가지고 있는 마일리지가 (\\d+)과 (\\d+)와 (\\d+)일 때$")
    public void vip_승객이_가지고_있는_마일리지가_과_와_일_때(int arg1, int arg2, int arg3) throws Throwable {
        mileage.addMileage(mike, arg1);
        mileage.addMileage(mike, arg2);
        mileage.addMileage(mike, arg3);
    }

    @Then("^VIP 승객의 보너스 포인트는 (\\d+)가 된다$")
    public void vip_승객의_보너스_포인트는_가_된다(int arg1) throws Throwable {

        mileage.calculateGivenPoints();
        assertThat(mileage.getPassengersPointsMap().get(mike).intValue()).isEqualTo(arg1);
    }
}
