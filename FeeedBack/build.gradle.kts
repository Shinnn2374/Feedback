plugins {
	java
	id("org.springframework.boot") version "3.5.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// ===== [ Core ] =====
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	// ===== [ Web & MVC ] =====
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6:3.1.2.RELEASE")

	// ===== [ Security ] =====
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	// ===== [ Database ] =====
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql") // или MySQL
	implementation("org.flywaydb:flyway-core:9.22.3") // Опционально (миграции)

	// ===== [ Validation ] =====
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// ===== [ JWT (Опционально) ] =====
	implementation("io.jsonwebtoken:jjwt-api:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.3")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.3")

	// ===== [ Lombok ] =====
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// ===== [ Дополнительные ] =====
	implementation("org.springframework.boot:spring-boot-starter-mail") // Email
	implementation("org.apache.poi:poi:5.2.3") // Excel-экспорт
	implementation("com.itextpdf:itextpdf:5.5.13.3") // PDF-генерация
	implementation("org.webjars:chartjs:3.9.1") // Графики
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0") // Swagger
}

tasks.withType<Test> {
	useJUnitPlatform()
}
