# gitcrawler
The project gitcrawler can crawl all the projects in GITHUB.
The project gitcrawler should run as:

java -jar gitcrawler.jar

You can also import the project into eclipse directly, find the main class cn.yyx.research.gitcrawler.gitcrawler.App, then right click it and choose 'Run as Java Applications'.

In the console or the command line, you can type 'start' directly, the gitcrawler will start to crawl projects on github.

Before you type 'start', you could type 'initial para1:para2:para3:para4:para5' to initial the crawler.

Here is meanings of the five parameters:para1 to para5:

para1 : number of threads used to crawl the projects.

para2 : the minimum stars of a project, all projects with smaller number of stars than this number will be ignored.

para3 : the range of stars. Only projects with stars between 'starbegin' and 'starbegin + starrange' will be crawled.

para4 : language, the language of projects, such as Java, C, C#, etc.

para5 : dest directory, where to put the crawled projects.

Here is an overall example:

initial 10:50:5:java:~/HomeSpace/AllZipFile
start

The two commands will use 10 threads to crawl Java projects with stars between 50-55, the crawled projects will be put into the directory '~/HomeSpace/AllZipFile' in Linux. The way is similar on Windows. I guess it also works on Mac because I did not use any external library in this project.

Note that, the projects crawled is of zip format. We need to unzip all these zip files. In https://github.com/yangyixiaof/gitcrawler/tree/master/gitcrawler/UnzipScript, we offer a linux bash script unzipAll.sh to unzip all these zip files.

The usage of the unzipAll.sh is as follows:
./unzipAll.sh WhereTheZipFilesExist
WhereTheZipFilesExist is the path to the directory you put the crawled projects, WhereTheZipFilesExist should be the same as 'para5' described above.

Here is an example:

./unzipAll.sh ~/HomeSpace/AllZipFile

The unzipped files will be put into the directory named 'UnzipPool' in the current directory where your command line is located.

The project programprocessor translates Java to IR.
The project program processor should run as:
java -jar programprocessor.jar

then, input command:
start PathToSourceRepository PathToWhereIRIsPut

an example is:
start /home/yyx/CodeComletionTestSpace/CodeSource /home/yyx/CodeComletionTestSpace/IR_yyx
The path /home/yyx/CodeComletionTestSpace/CodeSource is where the source codes are put.
The path /home/yyx/CodeComletionTestSpace/IR_yyx is where the geneted IR is put.
