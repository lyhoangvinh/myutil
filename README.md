My-Utils
========

Common Android Utils can be used in almost android projects.

For documentation, please see source code 's comments.

Note: All modules are compiled with Java 8 language, use android support version 26.1.0

Download
--------

Gradle:

Add the JitPack repository to your project level 's gradle file

```groovy
allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```
  
Add the dependencies to module 's gradle file

* Using SNAPSHOT version (see [JitPack][2] for documentation):
```groovy
    implementation 'com.github.lyhoangvinh:myutil:-90cfd8d01e-1'
```

Other options: [https://jitpack.io/#lyhoangvinh/myutil/]

License
=======

    Copyright 2018 Ly Hoang Vinh.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

[1]: https://github.com/lyhoangvinh/myutils/
[2]: https://jitpack.io/docs/#snapshots
[3]: https://jitpack.io/#lyhoangvinh/myutils

