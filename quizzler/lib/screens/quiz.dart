import 'package:flutter/material.dart';

class QuizPages extends StatefulWidget {
  const QuizPages({Key? key}) : super(key: key);

  @override
  State<QuizPages> createState() => _QuizPagesState();
}

class _QuizPagesState extends State<QuizPages> {
  @override
  Widget build(BuildContext context) {
    return SafeArea(
      child: Scaffold(
        appBar: AppBar(
          title: const Text(
            'Quizzing!',
            style: TextStyle(
              fontSize: 40,
            ),
          ),
        ),
        body: ListView(
          children: const [
             GameTile(field1: 'Create a Quiz',
                field2: 'Create your own Quiz \n Play with your friends',
                field3: 'Create'),
             GameTile(
                field1: 'Play With Friends',
                field2: 'Invite your fiends \n Compete and win together',
                field3: 'Invite Friends'),
            GameTile(
                field1: 'Play With Friends',
                field2: 'Invite your fiends \n Compete and win together',
                field3: 'Invite Friends'),
            GameTile(
                field1: 'Play With Friends',
                field2: 'Invite your fiends \n Compete and win together',
                field3: 'Invite Friends'),
          ],
        ),
      ),
    );
  }
}

class GameTile extends StatelessWidget {
  final String field1, field2, field3;
  const GameTile({super.key, required this.field1, required this.field2, required this.field3});
  @override
  Widget build(BuildContext context) {
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
}
