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
            'Authorization': 'Bearer v^1.1#i^1#r^0#p^3#f^0#I^3#t^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',
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
              <h3><span className="font-bold">Item Name: </span>{item.title}</h3>
              {info === 'false' ? null : <img src={item.image.imageUrl} alt={item.title} />}
              <p><span className="font-bold">Price: </span>${item.price.value} {item.price.currency}</p>
              <a href={item.itemWebUrl} target="_blank" rel="noopener noreferrer"><span className="font-bold">View on eBay</span></a>
              <hr />
            </div>
          ))}
        </div>
      );
  
};

export default EbayRequest;
