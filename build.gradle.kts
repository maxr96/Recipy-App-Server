import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.10"
    id("org.springframework.boot") version "2.3.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("com.google.cloud.tools.jib") version "2.6.0"
}

group = "com.recipeApp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["azureVersion"] = "2.3.5"

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}
val liquibaseRuntime by configurations

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.microsoft.azure:azure-active-directory-spring-boot-starter")
    implementation("com.microsoft.azure:azure-spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.hibernate:hibernate-core:5.4.21.Final")
    implementation ("com.microsoft.sqlserver:mssql-jdbc:9.1.0.jre11-preview")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly ("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("org.springdoc:springdoc-openapi-ui:1.4.8")
    api("io.jsonwebtoken:jjwt-api:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.2")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.2")
}

dependencyManagement {
    imports {
        mavenBom("com.microsoft.azure:azure-spring-boot-bom:${property("azureVersion")}")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}