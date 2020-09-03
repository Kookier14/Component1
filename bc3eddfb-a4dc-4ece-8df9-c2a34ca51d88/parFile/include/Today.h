#ifndef Today_HEADER
#define Today_HEADER

#include "../IDLGenerate/TodayIDL.h"
#include "../IDLGenerate/TodayIDLSupport.h"
#include "ndds/ndds_requestreply_cpp.h"

namespace ECOM{
namespace Port{
namespace OriginalData{
namespace Publish{
class OriginalData
{
public:
 	PostDataDataWriter *PostData_writer;
	PostData *instance;
public:
	OriginalData() {};
};//END class OriginalData
}//END namespace Publish

namespace Consume{
class OriginalData
{
public:
	virtual void DataProcess(PostData *pinstance) = 0;
};

class ThreadForConsumePortOriginalData
{
public:
	ECOM::Port::OriginalData::Consume::OriginalData *pPort;
	PostData instance;
	PostData *pinstance;
public:
	ThreadForConsumePortOriginalData(){
		pinstance = &instance;
	}

	void invoke()
	{
		pPort->DataProcess(pinstance);
	}

};
}//END namespace Consume
}//END namespace OriginalData
}//END Port
}//END ECOM

namespace ECOM{
namespace Interface{
namespace TransformParam{
namespace Provide{
class TransformParam{
public:
	connext::Replier<uiadd_params,uiadd_ret> *replier;

	virtual int uiadd(int &a, const int b) = 0;

};
}//END namespace Provide

namespace Require {
class TransformParam {
public:
	connext::Requester<uiadd_params,uiadd_ret> *requester;

	virtual int uiadd(int &a, const int b) = 0;
};

}//END namespace Require
}//END namespace TransformParam
}//END interface
}//END ECOM

#endif