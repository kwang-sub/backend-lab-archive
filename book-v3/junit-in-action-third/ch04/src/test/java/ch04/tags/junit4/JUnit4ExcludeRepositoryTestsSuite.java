package ch04.tags.junit4;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Categories.class)
@Categories.ExcludeCategory(RepositoryTests.class)
@Suite.SuiteClasses({Junit4CustomerTest.class, JUnit4CustomerRepositoryTest.class})
public class JUnit4ExcludeRepositoryTestsSuite {

}
