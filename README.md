# FRC_3464_2018

FIRST team 3464's robot code. This was code I wrote for team SimCity when I was a senior at Simsbury High School. It deploys to
a RoboRIO from a laptop, and uses four USB joysticks as operator input. Unlike past years, the autonomous period performs
several different procedures, depending on the starting position of the robot, and the randomized position of its target.

## Challenge: FIRST POWER UP

FIRST POWER UP, the 2018 FIRST Robotics Competition game, includes two alliances of video game characters and their human
operators who are trapped in an arcade game. Both alliances are working to defeat the boss in order to escape! Each three-team
alliance prepares to defeat the boss in three ways:

**1. Control the Switches and the Scale.** Robots collect Power Cubes and place them on Plates to control Switches or the Scale.
When the Scale or their Switch is tipped in their favor, it is considered owned by that Alliance. Alliances work to have
Ownership for as much time as possible.
 
**2. Earn Power Ups.** Robots deliver Power Cubes to their humans who then place them into the Vault earning the Alliance Power
Ups. Alliances use Power Ups to gain a temporary advantage during the Match. There are three Power Ups available to teams:
Force, Boost, and Levitate.

* Force gives the alliance ownership of the Switch, Scale, or both for a limited period of time
* Boost doubles the rate points are earned for a limited period of time 
* Levitate gives a robot a free climb

**3. Climb the Scale.** Robots Climb the Scale in order to be ready to Face The Boss.

Each match begins with a 15-second Autonomous period in which Robots operate only on pre-programmed instructions. During this
period, Robots work to support the three efforts listed above as well as earn points for crossing their Auto Line.

## Design: Command based programming

WPILib supports a method of writing programs called "Command based programming". Command based programming is a design pattern
to help you organize your robot programs. Some of the characteristics of robot programs that might be different from other
desktop programs are:
- Activities happen over time, for example a sequence of steps to shoot a Frisbee or raise an elevator and place a tube on a
goal.
- These activities occur concurrently, that is it might be desirable for an elevator, wrist and gripper to all be moving into a
pickup position at the same time to increase robot performance.
- It is desirable to test the robot mechanisms and activities each individually to help debug your robot.
- Often the program needs to be augmented with additional autonomous programs at the last minute, perhaps at competitions, so
easily extendable code is important.

Command based programming supports all these goals easily to make the robot program much simpler than using some less structured
technique.

### Analysis: Pros

It became obvious early on that last year's method of procedural programming wasn't going to cut it for this year's auto. One
auto already took too many lines of code for me to grok, and there would be at least six different autos to choose from when
the game started. Programming them all this way could have easily taken thousands of lines of code, and didn't take advantage
of the resources wpilib and java 8 provided.

The wpilib Command Scheduler both greatly simplified our auto code, and added the functionality of multitasking during auto.
Instead of being buried endless nested loops and recursion, the auto procedures were now clearly laid out in sequences of
reusable commands. This meant that it was easy to have the robot lift its cube and start driving at the same time. Furthermore,
java 8 functional interfaces allowed parts of commands to be decoupled from their classes, which allowed us to use the same
class to perform similar tasks. For example, the Drive command can move the robot for either a timer duration or an encoder
distance, using the same code. We used encoders when driving across the field, where precise distances mattered, but a timer 
when driving into a wall, to avoid delaying the auto while the encoders could not reach their target distance.

### Analysis: Cons

The entry barrier when reading command based code you didn't write is a little higher; at any time in execution, active code
might be spread between the operator interface, the robot map, the game mode methods, and multiple commands. 

## Implementation: Hardware mapping and the Operator interface

Good: the sample wpilib project provided a RobotMap class that centralized the physical inputs and outputs exposed by the API.
This allowed for quick testing of new hardware, and even allowed us to test the robot code on other robots by disabling and
remapping various components.

Okay: The sample also provided an OI class that managed inputs, which included several ways of running commands in response to
button presses. We didn't use this feature extensively, as we wanted our teleop code to have precise control over the robot.

# Result: Could have done better

An unexplained glitch prevented the autonomous code from starting in our first seven competition matches. Restarting our robot
after it connected to the FMS fixed this glitch for an unknown reason, but by the time I randomly guessed this would work around
the issue the damage was done. This problem never occurred when we tested our robot with our own driver station. The autonomous
code successfully dropped our cube into our scale every time it ran, and we successfully added more functionality to it during the
competition. We ended up competing in the final rounds of two regional competitions. Overall it was a good learning experience,
but a disappointing outcome.
