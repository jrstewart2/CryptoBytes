import { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Nav from './Nav.jsx';
import Details from './Details.jsx';
import { Button, Container, Row } from 'react-bootstrap';
import { Link, useNavigate } from 'react-router-dom';

const Remove = () => {
    const { id } = useParams();
    const [crypto, setCrypto] = useState();
    const [authUser, setAuthUser] = useState("");
    const [token, setToken] = useState("");
    const [url, setUrl] = useState("");
    const navigate = useNavigate();

    const removeCrypto = () => {

        const deleteResponse = axios.delete(url, {}, {
            headers: {Authorization: `Bearer ${token}`},})
        console.log(deleteResponse);
        navigate("/portfolio");
    }


    useEffect(() => {

        setToken(localStorage.getItem('token'));
        setAuthUser(localStorage.getItem('username'));
        const cryptoURL = `http://localhost:8080/api/portfolio/${authUser}/${id}`
        setUrl(cryptoURL);
        
        const getCrypto = async () => {
            const res = await axios.get(url, {}, {
                headers: {Authorization: `Bearer ${token}`},})
            setCrypto(res.data[0]);
        };
        getCrypto();
      }, [id]);

    return crypto && (
        <> 
            <Nav />
            <Container>
              <Row xs={'auto'} md={'auto'} className="g-4">
   
              <Details
                    id={id}
                    name={crypto.name}
                    crypto={crypto.crypto}
                    />

                <h3>Are you sure you want to Remove {id} From your Portfolio?</h3>
                <Link to="/portfolio"><Button variant="danger" onClick={removeCrypto}>Remove</Button></Link>
                <Link to="/portfolio"><Button variant="primary">Back to Safety</Button></Link>
                
                
              </Row>
            </Container>
        </>
    )
}
export default Remove;