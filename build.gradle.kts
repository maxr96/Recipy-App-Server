import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    val kotlinVersion = "1.4.10"
    id("org.springframework.boot") version "2.3.4.RELEASE"
    id("io.spring.dependency-management") version "1.0.10.RELEASE"
    kotlin("jvm") version kotlinVersion
    kotlin("plugin.spring") version kotlinVersion
    kotlin("plugin.allopen") version kotlinVersion
    id("org.jetbrains.kotlin.plugin.jpa") version kotlinVersion
    id("org.liquibase.gradle") version "2.0.4"
}

group = "com.recipeApp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_13

repositories {
    mavenCentral()
}

extra["azureVersion"] = "2.3.3"

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
    implementation ("org.mariadb.jdbc:mariadb-java-client:2.7.0")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly ("com.h2database:h2")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("org.liquibase:liquibase-gradle-plugin:2.0.4")
    liquibaseRuntime("org.liquibase.ext:liquibase-hibernate5:3.8")
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
        jvmTarget = "13"
    }
}
//TODO: Follow this post
// https://stackoverflow.com/questions/44862889/how-to-use-liquibase-to-generate-a-changelog-from-diffs-between-a-database-and-p
// to create custom liquibaseDiffChangelog task
//task("liquibaseDiffChangelog", args=type:Java) {
//        group = "liquibase"
//
//        classpath sourceSets.main .runtimeClasspath
//                classpath configurations . liquibase
//                main = "liquibase.integration.commandline.Main"
//
//        args "--changeLogFile="+diffLog
//        args "--referenceUrl=hibernate:spring:com.mypackage.entity?hibernate.physical_naming_strategy=org.springframework.boot.orm.jpa.hibernate.SpringPhysicalNamingStrategy&dialect=org.hibernate.dialect.MySQL5Dialect"
//        args "--username=<username>"
//        args "--password=<password>"
//        args "--url=jdbc:mysql://<db_host_and_port>/<db_name>"
//        args "--referenceDriver=liquibase.ext.hibernate.database.connection.HibernateDriver"
//        args "diffChangeLog"
//    }
//}

