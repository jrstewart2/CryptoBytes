import { useState, useEffect } from "react";
import axios from 'axios';


const Loggedin = () => {
    const [username, setProfile] = useState("")

    useEffect(() => {
        const getProfile = async () => {
            const token = localStorage.getItem('token');
            console.log(token)
            const loginURL = 'http://localhost:8080/api/auth/login';

            const response = await axios.get(loginURL, {
                headers: {Authorization: `Bearer ${token}`},});
            
            const data = response.data;   
            setProfile(data);
            localStorage.setItem('username', data)
        };
    getProfile();
    }, []);

    return username && (
        <p>
            Welcome {username}. 
        </p>
        

    )
}

export default Loggedin;