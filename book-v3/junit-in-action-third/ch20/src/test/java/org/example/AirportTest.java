package org.example;

import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


class AirportTest {
    private Passenger mike;
    private Passenger james;

    @BeforeEach
    void setUp() {
        mike = new Passenger("Mike", false);
        james = new Passenger("James", true);
    }


    @DisplayName("Given 이코노미 항공편에서")
    @Nested
    class EconomyFlightTest {
        private Flight economyFlight;

        @BeforeEach
        void setUp() {
            economyFlight = new EconomyFlight("1");
        }

        @Nested
        @DisplayName("When 일반 승객은")
        class RegularPassenger {

            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하고 삭제도 가능하다.")
            void testEconomyFlightRegularPassengers() {
                assertThat(economyFlight.getId()).isEqualTo("1");
                assertThat(economyFlight.addPassenger(mike)).isTrue();
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).size()).isEqualTo(1);
                assertThat(new ArrayList<>(new ArrayList<>(economyFlight.getPassengersList())).get(0).getName()).isEqualTo("Mike");
                assertThat(economyFlight.removePassenger(mike)).isTrue();
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).size()).isEqualTo(0);
            }

            @DisplayName("Then 이코노미 항공편에 일반 승객을 중복해서 추가할 수 없다")
            @RepeatedTest(5)
            void testEconomyFlightVipPassengers(RepetitionInfo repetitionInfo) {
                for (int i = 0; i < repetitionInfo.getCurrentRepetition(); i++) {
                    economyFlight.addPassenger(mike);
                }
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).size()).isEqualTo(1);
                assertThat(new ArrayList<>(economyFlight.getPassengersList())).containsExactly(mike);
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).get(0).getName()).isEqualTo("Mike");
            }
        }

        @Nested
        @DisplayName("When VIP 승객은")
        class VipPassenger {
            @Test
            @DisplayName("Then 이코노미 항공편에서 추가가 가능하지만 삭제는 불가능하다")
            void testEconomyFlightVipPassenger() {
                assertThat(economyFlight.getId()).isEqualTo("1");
                assertThat(economyFlight.addPassenger(james)).isTrue();
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).size()).isEqualTo(1);
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).get(0).getName()).isEqualTo("James");
                assertThat(economyFlight.removePassenger(james)).isFalse();
                assertThat(new ArrayList<>(economyFlight.getPassengersList()).size()).isEqualTo(1);
            }
        }
    }

    @DisplayName("Given 비즈니스 항공편에서")
    @Nested
    class BusinessFlightTest {
        private Flight businessFlight;

        @BeforeEach
        void setUp() {
            businessFlight = new BusinessFlight("2");
        }

        @DisplayName("When 일반 승객은")
        @Nested
        class RegularPassenger {
            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가, 삭제 모두 불가능하다")
            void testBusinessFlightRegularPassenger() {
                assertThat(businessFlight.addPassenger(mike)).isFalse();
                assertThat(businessFlight.getPassengersList().size()).isEqualTo(0);
                assertThat(businessFlight.removePassenger(mike)).isFalse();
                assertThat(businessFlight.getPassengersList().size()).isEqualTo(0);
            }
        }

        @DisplayName("When VIP 승객은")
        @Nested
        class VipPassenger {
            @Test
            @DisplayName("Then 비즈니스 항공편에서 추가는 가능하지만 삭제는 불가능하다")
            void testBusinessFlightVipPassenger() {
                assertThat(businessFlight.addPassenger(james)).isTrue();
                assertThat(businessFlight.getPassengersList().size()).isEqualTo(1);
                assertThat(businessFlight.removePassenger(james)).isFalse();
                assertThat(businessFlight.getPassengersList().size()).isEqualTo(1);
            }
        }
    }

    @DisplayName("Given 프리미엄 항공편에서")
    @Nested
    class PremiumFlightTest {
        private Flight premiumFlight;

        @BeforeEach
        void setUp() {
            premiumFlight = new PremiumFlight("3");
        }

        @DisplayName("When 일반 승객은")
        @Nested
        class RegularPassenger {
            @DisplayName("Then 프리미엄 항공편에서 추가 또는 삭제가 불가능하다")
            @Test
            void testPremiumFlightRegularPassenger() {
                assertThat(premiumFlight.addPassenger(mike)).isFalse();
                assertThat(premiumFlight.getPassengersList().size()).isEqualTo(0);
                assertThat(premiumFlight.removePassenger(mike)).isFalse();
                assertThat(premiumFlight.getPassengersList().size()).isEqualTo(0);
            }
        }


        @DisplayName("When VIP 승객은")
        @Nested
        class VipPassenger {
            @DisplayName("Then 프리미엄 항공편에서 추가 또는 삭제가 가능하다")
            @Test
            void testPremiumFlightVipPassenger() {
                assertThat(premiumFlight.addPassenger(james)).isTrue();
                assertThat(premiumFlight.getPassengersList().size()).isEqualTo(1);
                assertThat(premiumFlight.removePassenger(james)).isTrue();
                assertThat(premiumFlight.getPassengersList().size()).isEqualTo(0);
            }
        }
    }
}