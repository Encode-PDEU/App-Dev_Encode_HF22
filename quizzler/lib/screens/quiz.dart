import 'package:flutter/material.dart';

class QuizPages extends StatefulWidget {
  const QuizPages({Key? key}) : super(key: key);

  @override
  State<QuizPages> createState() => _QuizPagesState();
}

class _QuizPagesState extends State<QuizPages> {
  Widget gameTile(String field1, String field2, String field3) {
    return Container(
      height: 240,
      width: 200,
      margin: const EdgeInsets.fromLTRB(40, 20, 40, 10),
      decoration: BoxDecoration(
        color: Colors.lightBlue.shade100,
        borderRadius: const BorderRadius.all(Radius.circular(20)),
      ),
      child: Column(
        mainAxisAlignment: MainAxisAlignment.spaceEvenly,
        children: <Widget>[
          Text(
            field1,
            style: const TextStyle(
              fontSize: 25,
              fontWeight: FontWeight.bold,
            ),
          ),
          Text(field2,
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 15,
              )),
          ElevatedButton(
              onPressed: () {},
              style: ElevatedButton.styleFrom(
                shape: const StadiumBorder(),
                minimumSize: const Size(110, 30),
                maximumSize: const Size(120, 70),
                backgroundColor: Colors.white,
                foregroundColor: Colors.black,
              ),
              child: Text(field3)),
        ],
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        backgroundColor: Colors.indigo,
        appBar: AppBar(
          backgroundColor: Colors.indigo,
          title: const Text(
            'Quizzing!',
            style: TextStyle(
              fontSize: 40,
            ),
          ),
        ),
        body: ListView(
          children: [
            gameTile('Create a Quiz',
                'Create your own Quiz \n Play with your friends', 'Create'),
            gameTile(
                'Play With Friends',
                'Invite your fiends \n Compete and win together',
                'Invite Friends'),
            gameTile(
                'Play With Friends',
                'Invite your fiends \n Compete and win together',
                'Invite Friends'),
            gameTile(
                'Play With Friends',
                'Invite your fiends \n Compete and win together',
                'Invite Friends'),
          ],
        ),
      ),
    );
  }
}
