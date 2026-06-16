package com.example.querydslkotlin

import com.example.querydslkotlin.domain.Cat
import com.example.querydslkotlin.repository.CatJpaRepository
import com.example.querydslkotlin.repository.CatQueryRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@SpringBootTest
@Transactional
class CatTest {

    @Autowired
    private lateinit var catJpaRepository: CatJpaRepository


    @Autowired
    private lateinit var catQueryRepository: CatQueryRepository

    @BeforeEach
    fun init() {
        val parent = Cat.create("parent", 12, null)
        catJpaRepository.save(parent)

        val child = Cat.create("child", 5, parent)
        catJpaRepository.save(child)


        val parent1 = Cat.create("parent1", 11, null)
        catJpaRepository.save(parent1)

        val child1 = Cat.create("child1", 4, parent1)
        catJpaRepository.save(child1)
    }

    @Test
    fun findParentName() {
        val result = catQueryRepository.findByParentName("parent")
        assertThat(result.isPresent).isTrue()
    }

    @Test
    fun findByParentAge() {
        val result = catQueryRepository.findByMaxParentAge()
        assertThat(result.isPresent).isTrue()
        val child = result.get()
        assertThat(child.name).isEqualTo("child")
    }

    @Test
    fun getCatName() {
        val result = catQueryRepository.getCatName()
        assertThat(result.size).isEqualTo(4)
        assertThat(result).anyMatch { it == "child1" }
    }

    @Test
    fun caseBuilder() {
        val result = catQueryRepository.caseBuilder()
        assertThat(result.filter { it == "old" }.size).isEqualTo(1)
    }
}
