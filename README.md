# A11yInfo

A compact Android utility for querying device accessibility settings.

Cool things to do with this library:  
* Collect anonymized analytics to better understand the needs of your users and convince stakeholders of the importance of properly accommodating them.  
* Improve the in-app experience for users of certain accessibility features.

Uncool things to do with this library:  
* Link accessibility needs to [personally identifiable information](https://en.wikipedia.org/wiki/Personally_identifiable_information).  
* Degrade the in-app experience for users of certain accessibility features (e.g. by disabling access to app features or deliberately counteracting related UI changes).

# Getting Started

<ol>
  <li>Specify A11yInfo as a dependency in your <code>build.gradle</code> file:</li>
</ol>

```groovy
dependencies {
    implementation 'com.stkent:a11yinfo:1.0.0'
}
```

<ol start="2">
  <li>Create and query an <code>A11yInfo</code> instance in your code:</li>
</ol>

```java
public class ExampleApplication extends Application {
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        A11yInfo a11yInfo = new A11yInfo(this);
        System.out.println("Font scale: " + a11yInfo.getFontScale());
    }
    
}
```

A sample app is provided in the `app` directory.

Explore `A11yInfo`'s public API to learn more about the available accessibility information!

# License

    Copyright 2018 Stuart Kent
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.