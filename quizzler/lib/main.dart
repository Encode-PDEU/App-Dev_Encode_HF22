import 'package:flutter/material.dart';
import 'quiz_brain.dart';
import 'package:rflutter_alert/rflutter_alert.dart';
import 'screens/quiz.dart';
QuizBrain questionBank = QuizBrain();

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData.light().copyWith(
            colorScheme: ColorScheme.fromSwatch().copyWith(
              primary: Color(0xFF3F51B5),
            ),
            scaffoldBackgroundColor: const Color(0xFF3F51B5)
        ),
      home: QuizPages()
    );
  }
}

class QuizPage extends StatefulWidget {
  const QuizPage({Key? key}) : super(key: key);

  @override
  State<QuizPage> createState() => _QuizPageState();
}

class _QuizPageState extends State<QuizPage> {
  final List<Icon> scoreKeeper = [];

  void checkAnswer(bool userAnswer) {
    setState(() {
      if(questionBank.getAnswer() == userAnswer)
        scoreKeeper.add(Icon(Icons.check, color: Colors.green,));
      else
        scoreKeeper.add(Icon(Icons.close, color: Colors.red,));
      if(questionBank.questionNumber == questionBank.getLength()-1)
        Alert(
          context: context, type: AlertType.error, title: "THE END",
          desc: "You've completed the quiz",
          buttons: [
            DialogButton(
              child: Text(
                "RESET",
                style: TextStyle(color: Colors.white, fontSize: 20),
              ),
              color: Colors.green,
              onPressed: () {
                setState(() {
                  questionBank.questionNumber = 0;
                  scoreKeeper.clear();
                });
              },
              width: 120,
            )
          ],
        ).show();
      else questionBank.getNextQuestion();
    });
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      mainAxisAlignment: MainAxisAlignment.spaceBetween,
      crossAxisAlignment: CrossAxisAlignment.stretch,
      children: <Widget>[
        Expanded(
          flex: 5,
          child: Padding(
            padding: EdgeInsets.all(10),
            child: Center(
              child: Text(
                questionBank.getQuestion(),
                textAlign: TextAlign.center,
                style: TextStyle(
                  fontSize: 25,
                  color: Colors.white,
                ),
              ),
            ),
          ),
        ),
        Expanded(
          child: Padding(
            padding: EdgeInsets.all(15),
            child: TextButton(
              child: Text('True', style: TextStyle(fontSize: 20, color: Colors.white),),
              style: TextButton.styleFrom(
                backgroundColor: Colors.green,
              ),
              onPressed: () {
                checkAnswer(true);
              },
            ),
          ),
        ),
        Expanded(
          child: Padding(
            padding: EdgeInsets.all(15),
            child: TextButton(
              child: Text('False', style: TextStyle(fontSize: 20, color: Colors.white),),
              style: TextButton.styleFrom(
                  backgroundColor: Colors.red,
              ),
              onPressed: () {
                checkAnswer(false);
              },
            ),
          ),
        ),
        Row(children: scoreKeeper),
      ],
    );
  }
}

