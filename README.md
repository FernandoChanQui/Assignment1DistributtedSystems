Multiplayer Trivia Game
Multiplayer Trivia Game is a Java-based, real-time, and multi-player trivia quiz game based on socket-based client-server. The server creates trivia sessions that players can join and answer questions, competing for high scores on a dynamically updated leaderboard. Questions are divided into three categories: easy, medium, and hard, which will increase if a player reaches a certain score. A possible example of the score needed could be 30 and 60 points which keeps the game challenging but alluring. The leaderboard maintains the highest scores of players and is only updated when players are outdone by them, even if the players replay. Real-time notification when players enter or leave the game. Multi-client handling of the server through multithreading ensures that the communication runs smoothly and the quiz experience is fun for the participants.


Required tools to run the application

Windows PowerShell (prefered)
Steps to run the application
Step 1. Clone the repository
Step 2. Open at least three Windows PowerShell tabs one for the server and two for clients or as many clients as you want.

Step 3. Use cd command to navigate to the correct directory where the Server and Client directories are located.

Step 4. On the server directory compile all files

  javac *.java
Step 5. On the client directory compile all files

  javac *.java
Step 6. Run TriviaServer.java

    java TriviaServer
Step 7. Run TriviaClient.java

    java TriviaClient
Step 8. On the client terminal, it will first ask you for your username, and input your desired username.

Step 9. Once you input your username you will start playing, and answer the questions if you do not know the answer you can just leave it blank, and enter to submit the answer.

Step 10. Once you finish it will display the current leaderboard and it will ask you if you want to continue or not. if you want to continue type 'yes' and then go back to step 8.

Step 11. Otherwise, if you do not want to continue type 'no' it will display a goodbye message to let you know that you are disconnected from the server. After that, you can do  ctrl + c to go back to the terminal.

Answers to all questions
Easy Pool
    "What is the capital of Canada?", "Ottawa"
    "How many planets are in our solar system?", "Eight"
    "Who painted the Mona Lisa?", "Leonardo da Vinci"
    "What is the largest ocean on Earth?", "Pacific Ocean"
    "What is the chemical symbol for gold?", "Au"
    "Who wrote the play 'Romeo and Juliet'?", "William Shakespeare"
    "What is the square root of 144?", "12"
    "What is the opposite of hot?", "Cold"
    "How many sides does a triangle have?", "Three"
    "What is the largest country in the world by land area?", "Russia"
Medium Pool
    "What is the longest river in North America?", "The Mississippi River"
    "Who was the first president of the United States?", "George Washington"
    "What is the chemical symbol for gold?", "Au" 
    "Who wrote the famous novel Pride and Prejudice?", "Jane Austen"
    "What is the square root of 169?", "13"
    "Who painted the Mona Lisa?", "Leonardo da Vinci"
    "What is the most famous musical composition by Ludwig van Beethoven?", "Symphony No. 9"
    "What is the philosophical concept of cogito ergo sum often associated with?", "René Descartes" 
    "Who is considered the father of psychoanalysis?", "Sigmund Freud"
    "In which sport is the term ace used?", "Tennis"
Hard Pool
    "What is the largest freshwater lake in the world?", "Lake Superior"
    "Who was the first person to walk on the moon?", "Neil Armstrong" 
    "What is the smallest unit of matter?", "Atom"
    "Who wrote the play Hamlet?", "William Shakespeare"
    "What is the value of pi to 10 decimal places?", "3.1415926536" 
    "Who is the composer of the opera The Magic Flute?", "Wolfgang Amadeus Mozart" 
    "What is the famous theory proposed by Albert Einstein?", "Theory of relativity" 
    "Who is the founder of modern psychology?", "Wilhelm Wundt"
    "What is the term for the study of the human mind and behavior?", "Psychology"
    "What is the name of the largest muscle in the human body?", "Gluteus Maximus"
