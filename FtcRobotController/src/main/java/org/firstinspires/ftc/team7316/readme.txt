The Hardware class is very badly implemented atm. 

I think that it would be nice to have a teleop opmode and an auto opmode (and by "nice" I mean necessary to do autonomous)

However, usually we initialize hardware within a class then access its various properties. But NOW, we have 2 opmodes, and we cannot initialize Hardware in both (I think, someone should PROOOBably check that). 

So, my idea was a singleton, which uses a class variable. Basically we have one class variable which is supposed to be the ONLY instance of the class. Problem is, Hardware needs to have the hardwareMap variable passed into the constructor to work. 

So, now the realllllllly baaaaad solution is aNother static method, this time called setHardwareMap, where the class variable is set to "new Hardware(hardwareMap)" and the hardwareMap is passed in. Obvious issue with this is that if someone forgets to call setHardwareMap they gonna be a LIIIIIITle bit confused as to why none of the hardware variables are working.

Someone has a better idea, right? Please.... if this is the final version I think it will tarnish our reputation for fanTASTICLY designed code.
