import React, { useState, useEffect } from 'react';

const EbayRequest = (props) => {
  const [item, setItem] = useState({});
  const value = props.keyword;
  const info=props.info;
  useEffect(() => {
    const fetchData = async () => {
    
      try {
        const response = await fetch('https://api.ebay.com/buy/browse/v1/item_summary/search?q='+value+'&limit=1', {
          method: 'GET',
          headers: {
            'Authorization': 'Bearer v^1.1#i^1#I^3#f^0#r^0#p^1#t^H4sIAAAAAAAAAOVYbWwURRi+a6/VAhWDQA1BPbbAD3H35vZu72PtHR605Y7Q3sG1BZpU2I/Zdund7rIz1/bQYlMNKFQTgxA+jNao0Ub9oxK/Eo0EAz8kJBi+UhP4YQQJwQSU6g/E2Wsp10oA6SU28f5c5p133nmeZ953ZnZAT2nZ41uiW4bK7fcV9feAniK73T0VlJWWLHqguGhOiQ3kOdj7e+b3OHqLz1chIZ0y+FUQGbqGoLMrndIQnzOGqIyp8bqAVMRrQhoiHkt8MlK3gmcZwBumjnVJT1HOWHWI8roFLhB0cwrnFTgOKsSq3YjZoIcoTpA5H2Sh5JVkWfAESD9CGRjTEBY0HKJYwHpoEKRZXwMAvDvAs4Dx+UEz5WyCJlJ1jbgwgArn4PK5sWYe1ttDFRCCJiZBqHAsUpuMR2LVNfUNVa68WOERHZJYwBk0trVUl6GzSUhl4O2nQTlvPpmRJIgQ5QoPzzA2KB+5AeYe4Oek5jyAC7IKB/xAFCHnKYiUtbqZFvDtcVgWVaaVnCsPNazi7J0UJWqIG6CER1r1JESs2mn9rcwIKVVRoRmiapZE1kYSCSpcrwttdYKJ6FpVk1WttZ5OrKqmJT/rFwNQBLTs83EBTmFHJhqONiLzuJmW6iSCJRpy1ut4CSSo4XhtQJ42xCmuxc2Igi1E+X7sDQ19XLO1qMOrmMFtmrWuME2EcOaad16B0dEYm6qYwXA0wviOnEQhSjAMVabGd+ZycSR9ulCIasPY4F2uzs5OptPD6GariwXA7VpTtyIptcG0QBFfq9aH/dU7D6DVHBUJkpFI5XHWIFi6SK4SAForFfYGPF7WM6L7WFjh8dZ/GPI4u8ZWRKEqxC96RJHze90sFAUJ+gpRIeGRJHVZOEjYLJ0WzHaIjRSZgZZInmXS0FRl3kPy0xNQIEnWoEJ7g4pCi5zso90KhABCUZSCgf9TodxtqiehZEJckFwvWJ7LOLg2kBBj6WCj4G+MpJbrq7uysfbOSNAvRlcGlHi0K65E5MZNy72hu62GW5JfmlKJMg1k/kIIYNV64USI6ghDeUL0kpJuwISeUqXs5FpgjyknBBNnkzCVIoYJkYwYRqwwe3XB6P3LbeLeeBfujPqPzqdbskJWyk4uVtZ4RAIIhspYJxAj6WmXVeu6QK4flnldDvWEeKvk5jqpWBOSw2xVefjKyeToMqhDYkyI9IxJbttM3LqBNejtUCPnGTb1VAqaTe4J13M6ncGCmIKTrbALkOCqMMkOW7cvyPkCfg6ACfGSckfpusm2JRViK3Ysu8drtWvsR37Ylvu5e+0HQK/96yK7HVSBBe5KMK+0uNFRPG0OUjFkVEFhkNqqkW9XEzLtMGsIqln0kG0InNsnXYwObGv/q3Pjz0922/LfGPpbwMOjrwxlxe6peU8OYO7NnhL39Ipy1gOCrI/IGGBBM6i82etwz3bMPLPoXP/pZukpu739y13iB5XTm872gPJRJ7u9xObotdsiL+y9sLVSWvZc3ydlA5e2/oC23/9ENfZt/ch7ckC/8u2mPd8dpmv6Lg89+is+9XTi4z3Z7upvdv5ysuVYnNlTMddVEf30+Td3JgZtJ7Y/9tWl7imvzF6cOQt6O8o6ovS1Hcnr7xrhzbu3Xe13SL+Xv/TTmVlVVzY8cm3/+901bzx7aP20C0cSi1v0vXs/5LTXzPhv19fve3vh6jMt0gkX3jxzF+348VSyafCLBfO2vOPt4FacP15EORxrlnXM2PXHn2cPbjj82cLLZYdXHYnVDqwtSlx9puJQ7/dT0mtePPbqptczR/nBBRd2rH7r9KzSwZdrokfrZ9S17q8YGPp898UDs1rnNzUePL6x7z3jweG1/BsA89C2/REAAA==',
            'X-EBAY-C-MARKETPLACE-ID': 'EBAY_US', 
            'X-EBAY-C-ENDUSERCTX': 'affiliateCampaignId=<ePNCampaignId>,affiliateReferenceId=<referenceId>',
          },
        });

        if (!response.ok) {
        console.log("Error");
          throw new Error('Network response was not ok');
        }

        const data = await response.json();
        setItem(data); 
        console.log(data);
      } catch (error) {
        console.error('Error fetching item:', error);
      }
    };

    fetchData();
  }, []); 

  return (
        <div>
          {item.itemSummaries && item.itemSummaries.map((item) => (
            <div key={item.itemId}>
              <h3>{item.title}</h3>
              {info === 'false' ? null : <img src={item.image.imageUrl} alt={item.title} />}
              <p>Price: ${item.price.value} {item.price.currency}</p>
              <a href={item.itemWebUrl} target="_blank" rel="noopener noreferrer">View on eBay</a>
              <hr />
            </div>
          ))}
        </div>
      );
  
};

export default EbayRequest;
