# Jenkins

### Configure Maven to bundle all dependencies in a single file and copy to a desired folder
1. Open `pom.xml` in your repository and put this in your `properties` field
```xml
<output.dir>${env.HOME}/Documents</output.dir>
```
In this example, we are storing the jar in `~/Documents`
2. In `plugins` field add this
```xml
<plugin>
   <artifactId>maven-assembly-plugin</artifactId>
   <version>3.1.1</version>
   <configuration>
      <descriptorRefs>
         <descriptorRef>jar-with-dependencies</descriptorRef>
      </descriptorRefs>
      <archive>
         <manifest>
            <addClasspath>true</addClasspath>
            <classpathPrefix>lib/</classpathPrefix>
            <mainClass>com.ems.springboot.Main</mainClass> <!--Here goes your main class-->
         </manifest>
      </archive>
      <outputDirectory>${output.dir}</outputDirectory>
   </configuration>
   <executions>
      <execution>
         <id>make-assembly</id>
         <phase>package</phase>
         <goals>
            <goal>single</goal>
         </goals>
      </execution>
   </executions>
</plugin>
```
3. To bundle everything run `mvn clean package -DskipTests`

### Installing and Configuring Jenkins
1. Open terminal and type the following code to download `jenkins.war` in your home directory.
```bash
sudo curl --progress-bar -O -L http://mirrors.jenkins.io/war-stable/latest/jenkins.war
```

2. Run `java -jar jenkins.war --httpPort=8080`.
3. Browse to <http://localhost:8080>.
4. Follow the instructions to complete the installation.

NOTE: No need to create admin account. In case you forget your password, it is stored in `~/.jekins/secrets/initialAdminPassword`

5. In your terminal, `cd` to your repository and type
```bash
echo "pipeline {
    agent any
    stages {
        stage('Clone') {
            steps {
                git branch: 'master', url: https://github.com/ashvinsharma/ems-springboot.git
            }
        }

        stage('Build') {
            steps {
                sh 'mvn --version'
                sh 'mvn clean package -DskipTests'
            }
        }
    }
}" > Jenkinsfile
```

6. In your browser, open [Jenkins](http://localhost:8080) and click on **New Item**.
![](https://jenkins.io/doc/book/resources/pipeline/classic-ui-left-column.png)

7. Type name for your project and choose pipeline from the list.
8. Select **Pipeline** tab. 
	1.  **Definition**: Pipeline from SCM
	2.  **SCM**: Git
		1. **Repository URL**: <your-repo-url>
		2. **Credentials**: <your-creds>
	3. **Script Path**: `Jenkinsfile`

9. Hit `Save` and `Apply`.
10. To check if everything is working click `Build Now`.
