package ex02

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class ProfileTest {

    private lateinit var profile: Profile
    private lateinit var question: Question
    private lateinit var criteria: Criteria

    @BeforeEach
    fun create() {
        profile = Profile("Bull Hockey, Inc.")
        question = BooleanQuestion(1, "Got bonuses?")
        criteria = Criteria()
    }

    @Test
    fun matchAnswersFalseWhenMustMathCriteriaNotNet() {
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(Criterion(Answer(question, Bool.TRUE), Weight.MustMatch))

        val matches = profile.matches(criteria)
        assertFalse(matches)
    }

    @Test
    fun matchAnswersTrueForAnyDontCareCriteria() {
        profile.add(Answer(question, Bool.FALSE))
        criteria.add(Criterion(Answer(question, Bool.TRUE), Weight.DontCare))

        val matches = profile.matches(criteria)
        assertTrue(matches)
    }

}