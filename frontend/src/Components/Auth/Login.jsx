import { useState } from 'react'
import Form from './Form.jsx';
import { Button } from 'react-bootstrap';
import axios from 'axios';
import { Link, useNavigate } from 'react-router-dom';

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [status, setStatus] = useState("");
    const navigate = useNavigate();

    const formHandler = (e) => {
        e.preventDefault()
        console.log(e)
    }

    const handleUserName = (e) => {
        setUsername(e.target.value);
        console.log(username)
    }

    const handlePassword = (e) => {
        setPassword(e.target.value);
        console.log(password)
    }

    const submit = () => {

        const tokenURL = "http://localhost:8080/api/auth/token";
        const data = {}
        const encoded = btoa(`${username}:${password}`)

        axios.post(tokenURL, data, {
            headers: {Authorization: `Basic ${encoded}`},})
        .then(res => {
            if (res.status == 200) {
                const token = res.data;
                localStorage.setItem("token", token);
                navigate("/loggedin");
            } else {
                setStatus("Failed"); 
            } 
        })
        console.log("STATUS: ", status)

    }

    return (
        <>
        <Form formHandler={formHandler} handleUserName={handleUserName} handlePassword={handlePassword} />
        <Button variant="primary" type="button" onClick={submit}>Log In</Button>
        <p>{status}</p>
        <div>
            <br />
            <p>Not got an account?</p>
            <Link to={`../signup`}><Button variant="warning" type="button">Register</Button></Link>
        </div>
        </>
    )
}

export default Login;