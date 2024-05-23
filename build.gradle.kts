plugins {
    id("java")
    id("org.springframework.boot") version "3.2.5"
}

group = "com.tui.mobile"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.named<Jar>("jar") {
    manifest {
        attributes["Main-Class"] = "com.tui.mobile.interview.Application"
        exclude("META-INF/*.SF", "META-INF/*.DSA", "META-INF/*.RSA")
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux:3.2.5")
    implementation("org.json:json:20090211")
    compileOnly ("org.projectlombok:lombok:1.18.32")
    annotationProcessor ("org.projectlombok:lombok:1.18.32")

    testCompileOnly ("org.projectlombok:lombok:1.18.32")
    testAnnotationProcessor ("org.projectlombok:lombok:1.18.32")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test:3.2.5")
}

tasks.test {
    useJUnitPlatform()
}