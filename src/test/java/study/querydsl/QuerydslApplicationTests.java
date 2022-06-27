package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;
import static study.querydsl.entity.QHello.hello;

@SpringBootTest
@Transactional
@Commit
class QuerydslApplicationTests {

	@PersistenceContext EntityManager em;

	@Test
	void contextLoads() {
		Hello data = new Hello();
		em.persist(data);

		JPAQueryFactory query = new JPAQueryFactory(em);

		Hello result = query
				.selectFrom(hello)
				.fetchOne();

		assertThat(result).isEqualTo(data);
		assertThat(result.getId()).isEqualTo(data.getId());
	}

}
