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
