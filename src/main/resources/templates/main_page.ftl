<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <title>Hello React!</title>
        <script src="//www.w3cschool.cn/statics/assets/react/react.min.js"></script>
        <script src="//www.w3cschool.cn/statics/assets/react/react-dom.min.js"></script>
        <script src="//www.w3cschool.cn/statics/assets/react/babel.min.js"></script>
    </head>
<body>
    <div id="example"></div>
    <script type="text/babel">
        ReactDOM.render(
            <h1>Hello, world!</h1>,
            document.getElementById('example')
        );

        class Comp1 extends React.Component {
         constructor(props) {
            super(props);
            this.handleLoad = this.handleLoad.bind(this);
         }

         componentDidMount() {
            window.addEventListener('load', this.handleLoad);
         }

         componentWillUnmount() {
           window.removeEventListener('load', this.handleLoad)
         }

         handleLoad() {
          console.log("!!!!!!!");
         }
        }
    </script>
</body>
</html>