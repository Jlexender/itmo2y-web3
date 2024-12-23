plugins {
    id("java")
    id("war")
}

group = "ru.lexender.ifmo"
version = "2.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("javax.faces:javax.faces-api:2.3")
    implementation("org.glassfish:javax.faces:2.3.9")

    compileOnly("javax.enterprise:cdi-api:2.0.SP1")
    implementation("org.jboss.weld.servlet:weld-servlet:2.4.8.Final")
    compileOnly("javax.servlet:servlet-api:2.5")

    compileOnly("org.projectlombok:lombok:1.18.34")
    annotationProcessor("org.projectlombok:lombok:1.18.34")

    implementation("org.postgresql:postgresql:42.7.4")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
